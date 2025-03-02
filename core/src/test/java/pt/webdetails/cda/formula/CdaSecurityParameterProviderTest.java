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


package pt.webdetails.cda.formula;

import junit.framework.TestCase;
import org.junit.Before;
import pt.webdetails.cpf.session.ISessionUtils;
import pt.webdetails.cpf.session.IUserSession;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CdaSecurityParameterProviderTest extends TestCase {
  private static String principalName = "principalName";
  private static String principalRoles = "principalRoles";
  private static String systemRoleNames = "systemRoleNames";
  private static String systemUserNames = "systemUserNames";

  private ISessionUtils sessionUtilsMock;
  private CdaSecurityParameterProvider cdaSecurityParameterProvider;
  private IUserSession userSessionMock;

  public CdaSecurityParameterProviderTest() {
    super();
  }

  @Before
  public void setUp() {
    sessionUtilsMock = mock( ISessionUtils.class );
    cdaSecurityParameterProvider = new CdaSecurityParameterProvider( sessionUtilsMock );
    userSessionMock = mock( IUserSession.class );
  }

  public void testGetParameter() {
    String name = null;
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    name = "test";
    doReturn( null ).when( sessionUtilsMock ).getCurrentSession();
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    name = principalName;
    doReturn( "admin" ).when( userSessionMock ).getUserName();
    doReturn( userSessionMock ).when( sessionUtilsMock ).getCurrentSession();
    assertEquals( cdaSecurityParameterProvider.getParameter( name ), "admin" );
    verify( userSessionMock, times( 1 ) ).getUserName();

    name = principalRoles;
    String[] authorities = null;
    doReturn( authorities ).when( userSessionMock ).getAuthorities();
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    authorities = new String[ 0 ];
    doReturn( authorities ).when( userSessionMock ).getAuthorities();
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    authorities = new String[] { "Administrator", "Test" };
    doReturn( authorities ).when( userSessionMock ).getAuthorities();
    assertEquals( cdaSecurityParameterProvider.getParameter( name ), "Administrator,Test" );
    verify( userSessionMock, times( 3 ) ).getAuthorities();

    name = systemUserNames;
    String[] systemPrincipals = null;
    doReturn( systemPrincipals ).when( sessionUtilsMock ).getSystemPrincipals();
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    doReturn( null ).when( sessionUtilsMock ).getCurrentSession();
    doReturn( systemPrincipals ).when( sessionUtilsMock ).getSystemPrincipals();
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    systemPrincipals = new String[ 0 ];
    doReturn( systemPrincipals ).when( sessionUtilsMock ).getSystemPrincipals();
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    systemPrincipals = new String[] { "Admin", "Manager" };
    doReturn( systemPrincipals ).when( sessionUtilsMock ).getSystemPrincipals();
    assertEquals( cdaSecurityParameterProvider.getParameter( name ), "Admin,Manager" );
    verify( sessionUtilsMock, times( 3 ) ).getSystemPrincipals();

    name = systemRoleNames;
    String[] systemAuthorities = null;
    doReturn( systemAuthorities ).when( sessionUtilsMock ).getSystemAuthorities();
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    systemAuthorities = new String[ 0 ];
    doReturn( systemAuthorities ).when( sessionUtilsMock ).getSystemAuthorities();
    assertNull( cdaSecurityParameterProvider.getParameter( name ) );

    systemAuthorities = new String[] { "Admin", "Manager" };
    doReturn( systemAuthorities ).when( sessionUtilsMock ).getSystemAuthorities();
    assertEquals( cdaSecurityParameterProvider.getParameter( name ), "Admin,Manager" );
    verify( sessionUtilsMock, times( 3 ) ).getSystemAuthorities();
  }

}
