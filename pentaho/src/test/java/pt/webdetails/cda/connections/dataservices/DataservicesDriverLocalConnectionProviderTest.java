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


package pt.webdetails.cda.connections.dataservices;

import org.junit.Test;
import org.pentaho.di.trans.dataservice.client.api.IDataServiceClientService;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class DataservicesDriverLocalConnectionProviderTest {

  @Test( expected = NullPointerException.class)
  public void getLocalConnectionProviderTestUrlRequired2() throws SQLException, MalformedURLException {
    DataservicesDriverLocalConnectionProvider provider = spy( new DataservicesDriverLocalConnectionProvider(
            () -> null
    ) );
    provider.createConnection( "user", "password" );
  }

  @Test( expected = NullPointerException.class)
  public void getLocalConnectionProviderTestUrlRequired3() throws SQLException, MalformedURLException {
    DataservicesDriverLocalConnectionProvider provider = spy( new DataservicesDriverLocalConnectionProvider(
            () -> new ArrayList<>()
    ) );
    provider.createConnection( "user", "password" );
  }

  @Test( expected = NullPointerException.class)
  public void getLocalConnectionProviderTestUrlRequired() throws SQLException, MalformedURLException {
    IDataServiceClientService dataServiceMock = mock( IDataServiceClientService.class );
    DataservicesDriverLocalConnectionProvider provider = spy(new DataservicesDriverLocalConnectionProvider(
      () -> Arrays.asList(dataServiceMock)
    ) );
    provider.createConnection( "user", "password" );
  }

  @Test( expected = SQLException.class)
  public void getLocalConnectionProviderTestDriverRequired() throws SQLException, MalformedURLException {
    IDataServiceClientService dataServiceMock = mock( IDataServiceClientService.class );
    DataservicesDriverLocalConnectionProvider provider = spy( new DataservicesDriverLocalConnectionProvider(
      () -> Arrays.asList( dataServiceMock )
    ) );
    provider.setUrl( "jdbc:pdi://localhost:8080/pentaho/kettle?local=true" );
    provider.createConnection( "user", "password" );
  }

}
