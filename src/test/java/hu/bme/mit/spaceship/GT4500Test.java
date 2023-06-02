package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;

  @BeforeEach
  public void init(){
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void firingTorpedo_All_noTorpedo(){
    when(primary.fire(0)).thenReturn(false);
    when(secondary.fire(0)).thenReturn(false);

    boolean res = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(true, res);
  }

  @Test
  public void firingTorpedo_ALL_justPrimary(){
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(0)).thenReturn(false);

    boolean res = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(true, res);
  }

  @Test
  public void firingTorpedo_ALL_justSecondary(){
    when(primary.fire(0)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    boolean res = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(true, res);
  }

  @Test
  public void firingTorpedo_Single_justSecondary(){
    when(primary.fire(0)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    boolean res = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, res);
  }

  @Test
  public void firingTorpedo_Single_hasTorpedoInBoth(){
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    boolean res = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, res);
  }

  @Test
  public void firingTorpedo_Single_justPrimary(){
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(0)).thenReturn(false);

    boolean res = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, res);
  }

}
