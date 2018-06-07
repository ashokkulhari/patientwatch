package com.reporting.dao;


import java.util.List;

import com.reporting.entity.State;

public interface StateDao {
	public void saveState(State state);
	public List<State> findAllStates();
}
