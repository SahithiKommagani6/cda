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


package pt.webdetails.cda.connections.scripting;

import java.util.ArrayList;

import org.dom4j.Element;
import pt.webdetails.cda.connections.AbstractConnection;
import pt.webdetails.cda.connections.ConnectionCatalog.ConnectionType;
import pt.webdetails.cda.connections.InvalidConnectionException;
import pt.webdetails.cda.dataaccess.PropertyDescriptor;


public class ScriptingConnection extends AbstractConnection {

  private ScriptingConnectionInfo connectionInfo;

  public ScriptingConnection( final Element connection ) throws InvalidConnectionException {
    super( connection );
  }

  public ScriptingConnection() {
  }

  protected void initializeConnection( final Element connection ) throws InvalidConnectionException {
    connectionInfo = new ScriptingConnectionInfo( connection );
  }

  public String getType() {
    return "scripting";
  }

  @Deprecated
  public ScriptingConnectionInfo getScriptingConnectionInfo() {
    return connectionInfo;
  }

  public boolean equals( final Object o ) {
    if ( this == o ) {
      return true;
    }
    if ( o == null || getClass() != o.getClass() ) {
      return false;
    }

    final ScriptingConnection that = (ScriptingConnection) o;

    if ( !connectionInfo.equals( that.connectionInfo ) ) {
      return false;
    }

    return true;
  }

  public int hashCode() {
    return connectionInfo.hashCode();
  }

  @Override
  public ConnectionType getGenericType() {
    return ConnectionType.SCRIPTING;
  }

  @Override
  public ArrayList<PropertyDescriptor> getProperties() {
    ArrayList<PropertyDescriptor> properties = new ArrayList<PropertyDescriptor>();
    properties.add(
      new PropertyDescriptor( "id", PropertyDescriptor.Type.STRING, PropertyDescriptor.Placement.ATTRIB ) );
    properties.add(
      new PropertyDescriptor( "language", PropertyDescriptor.Type.STRING, PropertyDescriptor.Placement.CHILD ) );
    properties.add(
      new PropertyDescriptor( "initscript", PropertyDescriptor.Type.STRING, PropertyDescriptor.Placement.CHILD ) );
    return properties;
  }

  public ScriptingConnectionInfo getConnectionInfo() {
    return connectionInfo;
  }
}
