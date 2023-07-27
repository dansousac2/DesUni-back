package com.wl.desafiounidac.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.desafiounidac.model.entity.BreakFast;
import com.wl.desafiounidac.model.entity.CollaboratorsAndItems;
import com.wl.desafiounidac.model.enums.SeqSql;
import com.wl.desafiounidac.model.repository.BreakFastRepository;
import com.wl.desafiounidac.presentation.dtos.BreakFastCollabsAndItemsDto;
import com.wl.desafiounidac.presentation.dtos.BreakFastDto;
import com.wl.desafiounidac.presentation.exceptions.BreakFastAlreadyExists;
import com.wl.desafiounidac.presentation.exceptions.CollaboratorNotFoundException;
import com.wl.desafiounidac.presentation.exceptions.CollaboratorRepetitionFindedException;
import com.wl.desafiounidac.presentation.exceptions.CurrentBreakFastNotFoundException;
import com.wl.desafiounidac.presentation.exceptions.ItemNotFindedException;
import com.wl.desafiounidac.presentation.exceptions.ItemRepetitionFindedException;

import jakarta.validation.Valid;

@Service
public class BreakFastService {

	@Autowired
	private BreakFastRepository repository;

	public List<BreakFast> getAllBreakFasts() {
		return repository.getAllBreakFasts();
	}

	public BreakFast getCurrentBreakFast() throws CurrentBreakFastNotFoundException {

		// TODO adicionar lógica de simulação de quando chegar o dia do café

		BreakFast breakFast = repository.getBreakFast(LocalDate.now());

		if (breakFast == null) {
			throw new CurrentBreakFastNotFoundException("Sem café da manhã marcado para o dia de hoje");
		}

		return breakFast;
	}

	public BreakFast getBreakFastByDate(LocalDate date) throws CurrentBreakFastNotFoundException {

		BreakFast breakFast = repository.getBreakFast(date);

		if (breakFast == null) {
			throw new CurrentBreakFastNotFoundException("Sem café da manhã marcado para data informada");
		}

		return breakFast;
	}

	public boolean existsBreakFast(LocalDate date) {
		return repository.existsBreakFast(date) == 1;
	}

	public void createBreakFast(LocalDate date, @Valid Set<Integer> items, String collaboratorCpf,
			ItemService itemService, CollaboratorService collabService)
			throws BreakFastAlreadyExists, CollaboratorNotFoundException, ItemNotFindedException {

		if (!collabService.existsCollaboratorByCpf(collaboratorCpf)) {
			throw new CollaboratorNotFoundException("Colaborador(a) não encontrado(a) para o CPF informado");
		}

		if (existsBreakFast(date)) {
			throw new BreakFastAlreadyExists("Café da manhã já encontra-se cadastrado");
		}

		if (!existsSequence(SeqSql.SEQ_BREAK_FAST.toString().toLowerCase())) {
			repository.createSequenceBreakFast();
		}

		if (!existsSequence(SeqSql.SEQ_COLLAB_AND_ITEM.toString().toLowerCase())) {
			repository.createSequenceCollAndItem();
		}

		// pegar o futuro ID da Tabela BreakFast
		int nextBreakFastId = repository.verifyNextBreakFastId();

		// para cada item, adiciona na Tabela de Collaboradores e Itens os valores
		// referentes
		for (Integer itemId : items) {

			// lança excessão caso não encontre o item pelo ID
			itemService.getItemById(itemId);

			repository.insertIntoCollabAndItems(SeqSql.SEQ_COLLAB_AND_ITEM.toString().toLowerCase(), itemId,
					nextBreakFastId, collaboratorCpf);
		}

		// insere valores na Tabela Café da Manhã por segundo
		repository.createBreakFast(SeqSql.SEQ_BREAK_FAST.toString().toLowerCase(), date);

	}

	public void insertCollaboratorAndItems(@Valid BreakFastDto dto, CollaboratorService collabService,
			ItemService itemService) throws CollaboratorNotFoundException, ItemNotFindedException,
			CurrentBreakFastNotFoundException, CollaboratorRepetitionFindedException, ItemRepetitionFindedException {

		// verifica data e pega seu Id
		Integer breakFastId = getBreakFastByDate(dto.getDate()).getId();

		// verifica cpf do colaborador
		collabService.getCollaboratorByCpf(dto.getCollaboratorCpf());
		// verifica se já está no café da manhã marcado
		verifyCollaboratorRepetition(dto.getCollaboratorCpf(), breakFastId);

		for (Integer itemId : dto.getItemsId()) {
			// verifica se itens existem
			itemService.getItemById(itemId);
			// verifica se item já se encontra no café da manhã referenciado
			verifyItemRepetition(itemId, breakFastId);

			// fase de inserção de valores na tabela
			repository.insertIntoCollabAndItems(SeqSql.SEQ_COLLAB_AND_ITEM.toString().toLowerCase(), itemId,
					breakFastId, dto.getCollaboratorCpf());
		}

	}

	public List<CollaboratorsAndItems> getCollabAndItemsOfBreakFast(Integer breakFastId,
			CollaboratorsAndItemsService collabsAndItemsRepository) {

		return collabsAndItemsRepository.getCollabAndItemsOfBreakFast(breakFastId);
	}

	public void confirmeItemCollaboration(BreakFastCollabsAndItemsDto dto, CollaboratorService collabService, ItemService itemService) 
			throws CurrentBreakFastNotFoundException, CollaboratorNotFoundException, ItemNotFindedException {

		// verifica data e pega seu Id
		getBreakFastByDate(dto.getBreakFast().getDate());

		for(CollaboratorsAndItems collabAndItem : dto.getCollabsAndItems()) {
			// verifica cpf do colaborador
			collabService.getCollaboratorByCpf(collabAndItem.getCollaboratorId());
			// verifica o item
			itemService.getItemById(collabAndItem.getItemId());
			
			repository.confirmeItemCollaboration(dto.getBreakFast().getId(), collabAndItem.getCollaboratorId(), collabAndItem.getItemId(),
					collabAndItem.isBroughtItem());
		}
	}

	private boolean existsSequence(String sequenceName) {
		return repository.existsSequence(sequenceName) == 1;
	}

	private void verifyCollaboratorRepetition(String collaboratorCpf, Integer breakFastId)
			throws CollaboratorRepetitionFindedException {

		int result = repository.isCollaboratorInCollabAndItemTable(collaboratorCpf, breakFastId);
		if (result > 0) {
			throw new CollaboratorRepetitionFindedException(
					"Colaborador(a) já se encontra no café da manhã " + "marcado para esta data");
		}
	}

	private void verifyItemRepetition(Integer itemId, Integer breakFastId) throws ItemRepetitionFindedException {
		int result = repository.isItemInCollabAndItemTable(itemId, breakFastId);
		if (result > 0) {
			throw new ItemRepetitionFindedException(
					"Colaborador(a) já se encontra no café da manhã " + "marcado para esta data");
		}
	}
}
