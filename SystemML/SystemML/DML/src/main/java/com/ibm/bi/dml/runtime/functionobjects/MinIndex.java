/**
 * IBM Confidential
 * OCO Source Materials
 * (C) Copyright IBM Corp. 2010, 2014
 * The source code for this program is not published or otherwise divested of its trade secrets, irrespective of what has been deposited with the U.S. Copyright Office.
 */

package com.ibm.bi.dml.runtime.functionobjects;

import com.ibm.bi.dml.runtime.DMLRuntimeException;
import com.ibm.bi.dml.runtime.matrix.MatrixCharacteristics;
import com.ibm.bi.dml.runtime.matrix.io.MatrixIndexes;
import com.ibm.bi.dml.runtime.matrix.io.MatrixValue.CellIndex;


public class MinIndex extends IndexFunction
{
	@SuppressWarnings("unused")
	private static final String _COPYRIGHT = "Licensed Materials - Property of IBM\n(C) Copyright IBM Corp. 2010, 2013\n" +
                                             "US Government Users Restricted Rights - Use, duplication  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
	
	private static MinIndex singleObj = null;
	
	private MinIndex() {
		// nothing to do here
	}
	
	public static MinIndex getMinIndexFnObject() {
		if ( singleObj == null )
			singleObj = new MinIndex();
		return singleObj;
	}
	
	public Object clone() throws CloneNotSupportedException {
		// cloning is not supported for singleton classes
		throw new CloneNotSupportedException();
	}
	
	@Override
	public void execute(MatrixIndexes in, MatrixIndexes out) {
		long min = Math.min(in.getRowIndex(), in.getColumnIndex());
		out.setIndexes(min, min);
	}

	@Override
	public void execute(CellIndex in, CellIndex out) {
		int min = Math.min(in.row, in.column);
		out.set(min, min);
	}

	@Override
	public boolean computeDimension(int row, int col, CellIndex retDim) {
		int min=Math.min(row, col);
		retDim.set(min, min);
		return false;
	}
	
	public boolean computeDimension(MatrixCharacteristics in, MatrixCharacteristics out) throws DMLRuntimeException
	{
		long minMatrix=Math.min(in.numRows, in.numColumns);
		int minBlock=Math.min(in.numRowsPerBlock, in.numColumnsPerBlock);
		out.set(minMatrix, minMatrix, minBlock, minBlock);
		return false;
	}

}
