package com.pica.dao;

import com.pica.exception.SequenceException;

public interface SequenceDAO {
	long getNextSequenceId(String key) throws SequenceException;
	
	long getNextSequenceIdProfile(String key) throws SequenceException;

	long getNextSequenceIdSupervisor(String key) throws SequenceException;

	long getNextSequenceIdAgent(String key) throws SequenceException;
}
