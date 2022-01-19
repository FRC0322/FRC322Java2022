// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.fireteam322.frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import com.fireteam322.frc.robot.commands.AutomaticLED;
import com.fireteam322.frc.robot.commands.BasicAutonomous;
import com.fireteam322.frc.robot.commands.DashboardUpdater;
import com.fireteam322.frc.robot.commands.DoNothing;
import com.fireteam322.frc.robot.commands.DriveWithJoystick;
import com.fireteam322.frc.robot.commands.ForwardAutonomous;
import com.fireteam322.frc.robot.commands.RunRearCamera;
import com.fireteam322.frc.robot.commands.SimpleAutonomous;
import com.fireteam322.frc.robot.subsystems.AddressableLEDs;
import com.fireteam322.frc.robot.subsystems.Chassis;
import com.fireteam322.frc.robot.subsystems.Dashboard;
import com.fireteam322.frc.robot.subsystems.LED;
import com.fireteam322.frc.robot.subsystems.RearCamera;
import com.fireteam322.frc.robot.subsystems.RobotPower;
import com.fireteam322.frc.robot.utilities.F310Controller;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
// The robot's subsystems and commands are defined here...
Command m_autoCommand;
SendableChooser<Command> autonomousChooser = new SendableChooser<>();

private final AddressableLEDs m_AddressableLEDs = new AddressableLEDs();
private final Chassis m_chassis = new Chassis();
private final Dashboard m_dashboard = new Dashboard();
private final LED m_led = new LED();
private final RearCamera m_rearCamera = new RearCamera();
private final RobotPower m_robotPower = new RobotPower();

private final F310Controller m_driveStick = new F310Controller(Constants.DRIVE_STICK);
private final F310Controller m_manipulatorStick = new F310Controller(Constants.MANIPULATOR_STICK);

private final JoystickButton m_brakeButton = new JoystickButton(m_driveStick, F310Controller.Button.kA.getValue());

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
		// Assign default commands
		m_chassis.setDefaultCommand(new DriveWithJoystick(
						    ()->(m_driveStick.getRightTriggerAxis() - m_driveStick.getLeftTriggerAxis()),
						    ()->(m_driveStick.getLeftX()), m_chassis, m_brakeButton));

		m_dashboard.setDefaultCommand(new DashboardUpdater(m_dashboard));

		m_led.setDefaultCommand(new AutomaticLED(m_led, m_AddressableLEDs));

		m_rearCamera.setDefaultCommand(new RunRearCamera(m_rearCamera));

		// Setup the SendableChooser
		chooserSetup();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

	public Dashboard getDashboard() {
		return m_dashboard;
	}

	// Use this to setup the SendableChooser.
	private void chooserSetup() {
		// Add commands to Autonomous SendableChooser
		autonomousChooser.setDefaultOption("Do Nothing", new DoNothing());
		autonomousChooser.addOption("Basic Autonomous", new BasicAutonomous(m_chassis));
		autonomousChooser.addOption("Forward Autonomous", new ForwardAutonomous(m_chassis));
		autonomousChooser.addOption("Simple Autonomous", new SimpleAutonomous(m_chassis));
		// Add the Autonomous SendableChooser to the Shuffleboard
		m_dashboard.getAutonomousTab().add("Autonomous Mode", getChooser());
	}

	/**
	 * Use this to pass the SendableChooser to the Logger or the main {@link Robot} class.
	 *
	 * @return the SendableChooser<Command>
	 */
	public SendableChooser<Command> getChooser() {
		return autonomousChooser;
	}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
		m_autoCommand = autonomousChooser.getSelected();
		return m_autoCommand;
	}
}
