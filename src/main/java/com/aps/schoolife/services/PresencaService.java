package com.aps.schoolife.services;

import com.aps.schoolife.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresencaService {
    @Autowired
    private PresencaRepository presencaRepository;
}
