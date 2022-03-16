package com.fireteam322.frc.robot.utilities;

import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.GenericHID;
import java.util.HashMap;
import java.util.Map;

/**
 * Handle input from Logitech F310 controllers connected to the Driver Station.
 *
 * <p>This class handles F310 input that comes from the Driver Station. Each time a value is
 * requested the most recent value is returned. There is a single class instance for each controller
 * and the mapping of ports to hardware buttons depends on the code in the Driver Station.
 */
public class F310Controller extends GenericHID {
  /** Represents a digital button on an F310Controller. */
  public enum Button {
    kA(1),
    kB(2),
    kX(3),
    kY(4),
    kBumperLeft(5),
    kBumperRight(6),
    kBack(7),
    kStart(8),
    kStickLeft(9),
    kStickRight(10);

    private static final Map<Integer, Button> MY_MAP = new HashMap<Integer, Button>();

    static {
      for (Button button : values()) {
        MY_MAP.put(button.getValue(), button);
      }
    }

    private int value;

    private Button(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public static Button getByValue(int value) {
      return MY_MAP.get(value);
    }

    public String toString() {
      return name();
    }
  }

  /**
   * Construct an instance of a joystick. The joystick index is the USB port on the drivers station.
   *
   * @param port The port on the Driver Station that the joystick is plugged into.
   */
  public F310Controller(final int port) {
    super(port);

    // HAL.report(tResourceType.kResourceType_F310Controller, port);
    HAL.report(tResourceType.kResourceType_Joystick, port);
  }

  /**
   * Get the Left X axis value of the controller.
   *
   * @return The Left X axis value of the controller.
   */
  public double getLeftX() {
    return getRawAxis(0);
  }

  /**
   * Get the Right X axis value of the controller.
   *
   * @return The Right X axis value of the controller.
   */
  public double getRightX() {
    return getRawAxis(4);
  }

  /**
   * Get the Left Y axis value of the controller.
   *
   * @return The Left Y axis value of the controller.
   */
  public double getLeftY() {
    return getRawAxis(1);
  }

  /**
   * Get the Right Y axis value of the controller.
   *
   * @return The Right Y axis value of the controller.
   */
  public double getRightY() {
    return getRawAxis(5);
  }

  /**
   * Get the Left trigger axis value of the controller.
   *
   * @return The Left trigger axis value of the controller.
   */
  public double getLeftTriggerAxis() {
    return getRawAxis(2);
  }

  /**
   * Get the Right trigger axis value of the controller.
   *
   * @return The Right trigger axis value of the controller.
   */
  public double getRightTriggerAxis() {
    return getRawAxis(3);
  }

  /**
   * Read the value of the Left bumper button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getLeftBumper() {
    return getRawButton(Button.kBumperLeft.value);
  }

  /**
   * Read the value of the Right bumper button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getRightBumper() {
    return getRawButton(Button.kBumperRight.value);
  }

  /**
   * Whether the Left bumper was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getLeftBumperPressed() {
    return getRawButtonPressed(Button.kBumperLeft.value);
  }

  /**
   * Whether the Right bumper was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getRightBumperPressed() {
    return getRawButtonPressed(Button.kBumperRight.value);
  }

  /**
   * Whether the Left bumper was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getLeftBumperReleased() {
    return getRawButtonReleased(Button.kBumperLeft.value);
  }

  /**
   * Whether the Left bumper was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getRightBumperReleased() {
    return getRawButtonReleased(Button.kBumperRight.value);
  }

  /**
   * Read the value of the Left stick button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getLeftStickButton() {
    return getRawButton(Button.kStickLeft.value);
  }

  /**
   * Read the value of the Right stick button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getRightStickButton() {
    return getRawButton(Button.kStickRight.value);
  }

  /**
   * Whether the Left stick button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getLeftStickButtonPressed() {
    return getRawButtonPressed(Button.kStickLeft.value);
  }

  /**
   * Whether the Right stick button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getRightStickButtonPressed() {
    return getRawButtonPressed(Button.kStickRight.value);
  }

  /**
   * Whether the Left stick button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getLeftStickButtonReleased() {
    return getRawButtonReleased(Button.kStickLeft.value);
  }

  /**
   * Whether the Right stick button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getRightStickButtonReleased() {
    return getRawButtonReleased(Button.kStickRight.value);
  }

  /**
   * Read the value of the A button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getAButton() {
    return getRawButton(Button.kA.value);
  }

  /**
   * Whether the A button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getAButtonPressed() {
    return getRawButtonPressed(Button.kA.value);
  }

  /**
   * Whether the A button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getAButtonReleased() {
    return getRawButtonReleased(Button.kA.value);
  }

  /**
   * Read the value of the B button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getBButton() {
    return getRawButton(Button.kB.value);
  }

  /**
   * Whether the B button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getBButtonPressed() {
    return getRawButtonPressed(Button.kB.value);
  }

  /**
   * Whether the B button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getBButtonReleased() {
    return getRawButtonReleased(Button.kB.value);
  }

  /**
   * Read the value of the X button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getXButton() {
    return getRawButton(Button.kX.value);
  }

  /**
   * Whether the X button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getXButtonPressed() {
    return getRawButtonPressed(Button.kX.value);
  }

  /**
   * Whether the X button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getXButtonReleased() {
    return getRawButtonReleased(Button.kX.value);
  }

  /**
   * Read the value of the Y button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getYButton() {
    return getRawButton(Button.kY.value);
  }

  /**
   * Whether the Y button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getYButtonPressed() {
    return getRawButtonPressed(Button.kY.value);
  }

  /**
   * Whether the Y button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getYButtonReleased() {
    return getRawButtonReleased(Button.kY.value);
  }

  /**
   * Read the value of the back button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getBackButton() {
    return getRawButton(Button.kBack.value);
  }

  /**
   * Whether the back button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getBackButtonPressed() {
    return getRawButtonPressed(Button.kBack.value);
  }

  /**
   * Whether the back button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getBackButtonReleased() {
    return getRawButtonReleased(Button.kBack.value);
  }

  /**
   * Read the value of the start button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getStartButton() {
    return getRawButton(Button.kStart.value);
  }

  /**
   * Whether the start button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getStartButtonPressed() {
    return getRawButtonPressed(Button.kStart.value);
  }

  /**
   * Whether the start button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getStartButtonReleased() {
    return getRawButtonReleased(Button.kStart.value);
  }
}
