package com.fireteam322.frc.robot;

import com.fireteam322.frc.robot.commands.*;
import com.fireteam322.frc.robot.inf.RobotContrainerInterface;
import com.fireteam322.frc.robot.subsystems.*;
import com.fireteam322.frc.robot.utilities.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotTestContainerImpl implements RobotContrainerInterface {

  // Joystick
  private Joystick m_leftJoystick = new Joystick(1);
  private Joystick m_rightJoystick = new Joystick(2);
  private final Chassis m_chassis = new Chassis();

  // Right Trigger
  private JoystickButton brake = new JoystickButton(m_rightJoystick, 1);
  // Left Trigger
  private JoystickButton shooter = new JoystickButton(m_leftJoystick, 1);

  private JoystickButton feeder = new JoystickButton(m_leftJoystick, 2);

  // Subsystems
  private MotorBuilder runBottomeShooter = new MotorBuilder();

  private void setupDrive() {
    DriveWithJoysticks drive =
        new DriveWithJoysticks(
            () -> m_leftJoystick.getY() * m_leftJoystick.getRawAxis(3) * -1,
            () -> m_rightJoystick.getY() * m_leftJoystick.getRawAxis(3) * -1,
            m_chassis,
            brake);
    m_chassis.setDefaultCommand(drive);
  }

  public RobotTestContainerImpl() {

    // Driver Train
    setupDrive();

    // Setup the SendableChooser
    chooserSetup();
    // Configure the button bindings
    configureButtonBindings();
  }

  @Override
  public Command getAutonomousCommand() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void configureButtonBindings() {
    // TODO Auto-generated method stub

    runBottomeShooter
        .setDeviceID(Constants.LEFT_SHOOTER_MOTOR)
        .setSpeed(Constants.SHOOTER_SPEED)
        .setJoyStick(shooter)
        .setButtonActionAndSpeed(MotorBuilder.ACTION_HELD, m_leftJoystick.getRawAxis(3) * -1)
        .setButtonActionAndSpeed(MotorBuilder.ACTION_RELEASED, m_leftJoystick.getRawAxis(3) * -1);
  }

  @Override
  public void chooserSetup() {
    // TODO Auto-generated method stub

  }
}
