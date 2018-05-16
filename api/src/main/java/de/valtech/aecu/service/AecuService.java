/*
 *  Copyright 2018 Valtech GmbH
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>
 */
package de.valtech.aecu.service;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Service interface for AECU.
 * 
 * @author Roland Gruber
 */
@ProviderType
public interface AecuService {
    
    /**
     * Returns the AECU version.
     * 
     * @return version
     */
    String getVersion();
    
    /**
     * Returns a list of files that can be executed in the given path.
     * 
     * @param path file or folder
     * @return list of files that are executable
     * @throws AecuException error finding files (e.g. invalid path)
     */
    List<String> getFiles(String path) throws AecuException;
    
    /**
     * Executes the script at the given position.
     * 
     * @param path path of script
     * @return execution result
     * @throws AecuException error during execution
     */
    ExecutionResult execute(String path) throws AecuException;
    
    /**
     * Starts a new history entry.
     * 
     * @return history entry
     * @throws AecuException error setting up entry
     */
    HistoryEntry createHistoryEntry() throws AecuException;
    
    /**
     * Stores an execution run in existing history.
     * 
     * @param history history entry
     * @param result script execution result
     * @return updated history
     * @throws AecuException error inserting history entry
     */
    HistoryEntry storeExecutionInHistory(HistoryEntry history, ExecutionResult result) throws AecuException;
    
    /**
     * Finishes the history entry.
     * 
     * @param history open history entry
     * @return history entry
     * @throws AecuException error saving state
     */
    HistoryEntry finishHistoryEntry(HistoryEntry history) throws AecuException;
    
    /**
     * Returns the last history entries. The search starts at the newest entry.
     * 
     * @param startIndex start reading at this index
     * @param count number of entries to read
     * @return history entries (newest first)
     * @throws AecuException error reading history
     */
    List<HistoryEntry> getHistory(int startIndex, int count) throws AecuException;

}
