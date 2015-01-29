package com.joantolos.portfolio.template.engine;

import com.joantolos.portfolio.template.entity.PingPong;
import com.joantolos.portfolio.template.exception.DAOException;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
public interface PingEngine {
    
    public PingPong ping() throws DAOException;
}
