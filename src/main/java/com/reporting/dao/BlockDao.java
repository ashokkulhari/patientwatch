package com.reporting.dao;


import java.util.List;

import com.reporting.entity.Block;

public interface BlockDao {

	public void saveBlock(Block block);
	public List<Block> findAllBlocks();
	public List<String> findAllBlockNames();
}
