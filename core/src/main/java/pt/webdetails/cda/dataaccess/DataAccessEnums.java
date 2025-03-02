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


import java.util.Arrays;

public class DataAccessEnums {


  public enum DATA_ACCESS_TYPE {
    SIMPLE_DATA_ACCESS,
    COMPOUND_DATA_ACCESS
  }


  public enum ACCESS_TYPE {
    PRIVATE,
    PUBLIC
  }

  /**
   * DataAccess instanciation from type.
   */
  public enum DataAccessInstanceType {

    DENORMALIZED_MDX( "denormalizedMdx" ),
    DENORMALIZED_OLAP4J( "denormalizedOlap4j" ),
    JOIN( "join" ),
    KETTLE( "kettle" ),
    MDX( "mdx" ),
    MQL( "mql" ),
    OLAP4J( "olap4j" ),
    REFLECTION( "reflection" ),
    JSON_SCRIPTABLE( "jsonScriptable" ),
    SCRIPTABLE( "scriptable" ),
    SQL( "sql" ),
    UNION( "union" ),
    XPATH( "xPath" ),
    DATASERVICES( "dataservices", "sql" ),
    STREAMING_DATASERVICES( "streaming" );

    private String type;
    private String label;

    public String getType() {
      return this.type;
    }
    public String getLabel() {
      return this.label;
    }

    DataAccessInstanceType( String type ) {
      this.type = type;
      this.label = type;
    }

    DataAccessInstanceType( String type, String label ) {
      this.type = type;
      this.label = label;
    }

    public static DataAccessInstanceType parseType( String type ) {
      for ( DataAccessInstanceType dataAccess : DataAccessInstanceType.values() ) {
        if ( dataAccess.getType().equals( type ) ) {
          return dataAccess;
        }
      }
      return null;
    }
  }

  public enum ConnectionInstanceType {

    Metadata( "metadata.metadata" ),

    SQL_JDBC( "sql.jdbc" ),
    SQL_JNDI( "sql.jndi" ),

    MONDRIAN_JDBC( "mondrian.jdbc" ),
    MONDRIAN_JNDI( "mondrian.jndi" ),

    OLAP4J( new String[] { "olap4j", "olap4j.defaultolap4j" } ),

    SCRIPTING( "scripting.scripting" ),

    XPATH( "xpath.xPath" ),

    KETTLE_TRANS_FROM_FILE( "kettle.TransFromFile" ),

    DATASERVICES( "dataservices.dataservices" );

    private String[] types;

    public String[] getTypes() {
      return this.types;
    }

    ConnectionInstanceType( String type ) {
      this( new String[] { type } );
    }

    ConnectionInstanceType( String[] types ) {
      this.types = types;
    }

    public static ConnectionInstanceType parseType( String type ) {
      return parseType( new String[] { type } );
    }

    public static ConnectionInstanceType parseType( String[] types ) {
      for ( ConnectionInstanceType connection : ConnectionInstanceType.values() ) {
        for ( String type : types ) {
          if ( Arrays.asList( connection.getTypes() ).contains( type ) ) {
            return connection;
          }
        }
      }
      return null;
    }
  }

}
