package com.joantolos.portfolio.template.engine.impl;

import com.joantolos.portfolio.template.dao.PingPongDAO;
import com.joantolos.portfolio.template.entity.PingPong;
import com.joantolos.portfolio.template.exception.DAOException;
import com.joantolos.portfolio.template.engine.PingEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@Service
public class PingEngineImpl implements PingEngine {

    @Autowired
    PingPongDAO pingPongDAO;

    @Override
    public PingPong ping() throws DAOException {
        PingPong response = new PingPong();
        response.setId(1);
        response.setMotion(this.pingPongDAO.playPing());

        return response;
    }
}
