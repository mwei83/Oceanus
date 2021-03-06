/**
 * Copyright 2011-2013 FoundationDB, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bj58.sql.parser;

import com.bj58.sql.StandardException;

/**
 * A PartitionByColumn is a column in the PARTITION BY clause.
 *
 */
public class PartitionByColumn extends OrderedColumn 
{
    private ValueNode columnExpression;

    /**
     * Initializer.
     *
     * @param colRef The ColumnReference for the parition column
     */
    public void init(Object colRef) {
        this.columnExpression = (ValueNode)colRef;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        PartitionByColumn other = (PartitionByColumn)node;
        this.columnExpression = (ValueNode)getNodeFactory().copyNode(other.columnExpression,
                                                                     getParserContext());
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        if (columnExpression != null) {
            printLabel(depth, "columnExpression: ");
            columnExpression.treePrint(depth + 1);
        }
    }

    public ValueNode getColumnExpression() {
        return columnExpression;
    }

    /**
     * Accept the visitor for all visitable children of this node.
     *
     * @param v the visitor
     *
     * @exception StandardException on error
     */
    void acceptChildren(Visitor v) throws StandardException {

        super.acceptChildren(v);

        if (columnExpression != null) {
            columnExpression = (ValueNode)columnExpression.accept(v);
        }
    }

}
