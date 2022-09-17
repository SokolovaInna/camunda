package com.example.democamunda.service;

import com.example.democamunda.dto.CardDto;
import com.example.democamunda.dto.ProcessInstanceInfo;
import com.example.democamunda.repository.CardRepository;
import com.example.democamunda.util.StartProcess;
import org.springframework.stereotype.Service;

import static com.example.democamunda.common.NameCamundaProcess.ADD_CARD;
import static com.example.democamunda.common.NameCamundaProcess.PROBA;


@Service
public class CardService {
    private final CardRepository cardRepository;
    private final StartProcess startProcess;

    public CardService(CardRepository cardRepository, StartProcess startProcess) {
        this.cardRepository = cardRepository;
        this.startProcess = startProcess;
    }

public ProcessInstanceInfo startCardProcess(CardDto dto){

return startProcess.placeStart(ADD_CARD.getName());
}

public ProcessInstanceInfo startProbaProcess(){
        return startProcess.placeStart(PROBA.getName());
}


}
