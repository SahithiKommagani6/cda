/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/


package pt.webdetails.cda.dataaccess;

import org.dom4j.Element;
import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.extensions.datasources.scriptable.ScriptableDataFactory;
import pt.webdetails.cda.connections.ConnectionCatalog.ConnectionType;
import pt.webdetails.cda.connections.InvalidConnectionException;
import pt.webdetails.cda.connections.scripting.ScriptingConnection;
import pt.webdetails.cda.settings.UnknownConnectionException;

/**
 * Todo: Document me!
 */
public class ScriptableDataAccess extends PREDataAccess {

  public ScriptableDataAccess( final Element element ) {
    super( element );
  }

  public ScriptableDataAccess() {
  }

  public DataFactory getDataFactory() throws UnknownConnectionException, InvalidConnectionException {
    final ScriptingConnection connection = (ScriptingConnection) getCdaSettings().getConnection( getConnectionId() );

    final ScriptableDataFactory dataFactory = new ScriptableDataFactory();
    dataFactory.setLanguage( connection.getConnectionInfo().getLanguage() );
    dataFactory.setScript( connection.getConnectionInfo().getInitScript() );

    dataFactory.setQuery( "query", getQuery() );
    return dataFactory;
  }

  public String getType() {
    return "scriptable";
  }

  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.SCRIPTING;
  }

  // Do not log the script.
  @Override
  protected String getLogQuery() {
    return null;
  }
}
