// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.fireteam322.frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import com.fireteam322.frc.robot.commands.*;
import com.fireteam322.frc.robot.commands.Autonomous.*;
import com.fireteam322.frc.robot.subsystems.*;
import com.fireteam322.frc.robot.utilities.*;

/**
 * This class is where the bulk of the robot should be declared.
 * Since Command-based is a "declarative" paradigm, very little robot logic
 * should actually be handled in the {@link Robot} periodic methods (other than
 * the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and
 * button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	private static Command m_autoCommand;
	private static SendableChooser<Command> autonomousChooser = new SendableChooser<>();
	private static SendableChooser<Boolean> shooterModeChooser = new SendableChooser<>();
	private static boolean m_shooterMode;
	private static double m_shooterSpeed = Constants.LOW_SHOOTER_SPEED;

	private final Boolean m_lowGoal = Boolean.FALSE;
	private final Boolean m_highGoal = Boolean.TRUE;
	private final AddressableLEDs m_AddressableLEDs = new AddressableLEDs(Constants.ADDRESSABLE_LED_PORT,
			Constants.ADDRESSABLE_LED_LENGTH);
	private final Chassis m_chassis = new Chassis();
	private final RobotCamera m_frontCamera = new RobotCamera("Front Camera", Constants.FRONT_CAMERA_CHANNEL);
	private final RobotPower m_robotPower = new RobotPower();
	private final RearClimber m_rearClimber = new RearClimber();
	private final IntakeLifter m_lifter = new IntakeLifter();
	private final Feeder m_feeder = new Feeder();
	private final Intake m_intake = new Intake();
	private final Shooter m_shooter = new Shooter();

	private final F310Controller m_driveStick = new F310Controller(Constants.DRIVE_STICK);
	private final F310Controller m_manipulatorStick = new F310Controller(Constants.MANIPULATOR_STICK);

	private final Joystick m_leftDriveJoystick = new Joystick(Constants.LEFT_DRIVE_STICK);
	private final Joystick m_rightDriveJoystick = new Joystick(Constants.RIGHT_DRIVE_STICK);

	private final JoystickButton m_brakeButton = new JoystickButton(m_driveStick,
			F310Controller.Button.kBumperLeft.getValue());

	private final JoystickButton m_rearClimbButton = new JoystickButton(m_driveStick,
			F310Controller.Button.kX.getValue());
	private final JoystickButton m_rearClimbReverseButton = new JoystickButton(m_driveStick,
			F310Controller.Button.kY.getValue());
	private final JoystickButton m_intakeLiftButton = new JoystickButton(m_driveStick,
			F310Controller.Button.kA.getValue());
	private final JoystickButton m_intakeLowerButton = new JoystickButton(m_driveStick,
			F310Controller.Button.kB.getValue());

	private final JoystickButton m_feederButton = new JoystickButton(m_manipulatorStick,
			F310Controller.Button.kA.getValue());
	private final JoystickButton m_feederReverseButton = new JoystickButton(m_manipulatorStick,
			F310Controller.Button.kB.getValue());
	private final JoystickButton m_shooterButton = new JoystickButton(m_manipulatorStick,
			F310Controller.Button.kX.getValue());
	private final JoystickButton m_shooterReverseButton = new JoystickButton(m_manipulatorStick,
			F310Controller.Button.kY.getValue());
	private final JoystickButton m_intakeReverseButton = new JoystickButton(m_manipulatorStick,
			F310Controller.Button.kBumperLeft.getValue());
	private final JoystickButton m_intakeButton = new JoystickButton(m_manipulatorStick,
			F310Controller.Button.kBumperRight.getValue());

	private final JoystickButton m_brakeButtonJoystick = new JoystickButton(m_rightDriveJoystick,
			Constants.JOYSTICK_BRAKE_BUTTON);
	private final JoystickButton m_rearClimbButtonJoystick = new JoystickButton(m_rightDriveJoystick,
			Joystick.ButtonType.kTrigger.value);
	private final JoystickButton m_intakeLiftButtonJoystick = new JoystickButton(m_leftDriveJoystick,
			Joystick.ButtonType.kTrigger.value);
	private final JoystickButton m_intakeLowerButtonJoystick = new JoystickButton(m_rightDriveJoystick,
			Joystick.ButtonType.kTop.value);

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Assign default commands

		if (Constants.CLASSIC_MODE) {
			m_chassis.setDefaultCommand(new DriveWithJoysticks(() -> (m_leftDriveJoystick.getY() * ((m_rightDriveJoystick.getRawAxis(3) * -1 + 1.0) / 2.0)),
					() -> (m_rightDriveJoystick.getY() * ((m_rightDriveJoystick.getRawAxis(3) * -1 + 1.0) / 2.0)), m_chassis, m_brakeButtonJoystick));
		} else {
			m_chassis.setDefaultCommand(new DriveWithGamepad(
					() -> (m_driveStick.getRightTriggerAxis() - m_driveStick.getLeftTriggerAxis()),
					() -> (m_driveStick.getLeftX()), m_chassis, m_brakeButton));
		}

		if (Constants.DEBUG_MODE) {
			m_feeder.setDefaultCommand(new RunFeeder(m_feeder, () -> -m_manipulatorStick.getLeftY()));

			m_intake.setDefaultCommand(new RunIntake(m_intake, () -> -m_manipulatorStick.getRightY()));

			m_shooter.setDefaultCommand(new RunShooter(m_shooter, () ->
				(m_manipulatorStick.getRightTriggerAxis() - m_manipulatorStick.getLeftTriggerAxis())));

			m_lifter.setDefaultCommand(new RunIntakeLifter(m_lifter,
				m_manipulatorStick.getLeftX()));
			m_rearClimber.setDefaultCommand(new RunRearClimber(m_rearClimber,
				m_manipulatorStick.getRightX()));
		}

		m_AddressableLEDs.setDefaultCommand(new AutomaticAddressableLED(m_AddressableLEDs));

		m_frontCamera.setDefaultCommand(new RunFrontCamera(m_frontCamera));

		if (Constants.ROBOT_POWER)
			m_robotPower.setDefaultCommand(new StartRobotPower(m_robotPower));

		// Setup the SendableChooser
		chooserSetup();

		// Setup the ShooterModeChooser
		shooterModeSetup();

		// Set the Shooter speed
		setShooterSpeed();

		// Configure the button bindings
		configureButtonBindings();
	}

	/**
	 * Use this method to define your button->command mappings.
	 * Buttons can be created by instantiating a {@link GenericHID}
	 * or one of its subclasses ({@link edu.wpi.first.wpilibj.Joystick}
	 * or {@link XboxController}), and then passing it to a
	 * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		if (Constants.CLASSIC_MODE) {
			m_rearClimbButtonJoystick.whileActiveOnce(new RunRearClimber(m_rearClimber, Constants.CLIMBER_SPEED));
			m_intakeLowerButtonJoystick
					.whileActiveOnce(new RunRearClimber(m_rearClimber, Constants.CLIMBER_REVERSE_SPEED));
			m_intakeLiftButtonJoystick
					.whileActiveOnce(new RunIntakeLifter(m_lifter, Constants.INTAKE_LIFT_SPEED));
			m_intakeLowerButtonJoystick
					.whileActiveOnce(new RunIntakeLifter(m_lifter, Constants.INTAKE_LOWER_SPEED));
		} else {
			m_rearClimbButton.whileActiveOnce(new RunRearClimber(m_rearClimber, Constants.CLIMBER_SPEED));
			m_rearClimbReverseButton
					.whileActiveOnce(new RunRearClimber(m_rearClimber, Constants.CLIMBER_REVERSE_SPEED));
			m_intakeLiftButton.whileActiveOnce(new RunIntakeLifter(m_lifter, Constants.INTAKE_LIFT_SPEED));
			m_intakeLowerButton
					.whileActiveOnce(new RunIntakeLifter(m_lifter, Constants.INTAKE_LOWER_SPEED));
		}

		m_intakeButton.whileActiveOnce(new RunIntake(m_intake, () -> Constants.INTAKE_SPEED));
		m_intakeReverseButton.whileActiveOnce(new RunIntake(m_intake, () -> Constants.INTAKE_REVERSE_SPEED));

		m_feederButton.whileActiveOnce(new RunFeeder(m_feeder, () -> Constants.FEEDER_SPEED), true);
		m_feederReverseButton.whileActiveOnce(new RunFeeder(m_feeder, () -> Constants.FEEDER_REVERSE_SPEED), true);

		m_shooterButton.whileActiveOnce(new RunShooter(m_shooter, () -> m_shooterSpeed), true);
		m_shooterReverseButton.whileActiveOnce(new RunShooter(m_shooter, () -> Constants.SHOOTER_REVERSE_SPEED), true);
	}

	// Use this to setup the SendableChooser.
	private void chooserSetup() {
		// Add commands to Autonomous SendableChooser
		autonomousChooser.setDefaultOption("Do Nothing", new DoNothing());
		autonomousChooser.addOption("Simple Autonomous", new Simple(m_chassis));
		autonomousChooser.addOption("Low Shooter Autonomous",
				new ShootLowAndDrive(m_chassis, m_intake, m_feeder, m_shooter));
		autonomousChooser.addOption("High Shooter Autonomous",
				new ShootHighAndDrive(m_chassis, m_intake, m_feeder, m_shooter));
		SmartDashboard.putData("Autonomous Modes", autonomousChooser);
	}

	/**
	 * Use this to setup the shooter mode.
	 */
	private void shooterModeSetup() {
		shooterModeChooser.setDefaultOption("Low Goal", m_lowGoal);
		shooterModeChooser.addOption("High Goal", m_highGoal);
		SmartDashboard.putData("Shooter Mode", shooterModeChooser);
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

	/**
	 * Use this to pass the shooter mode elsewhere in this class.
	 *
	 * @return a boolean describing the shooter mode (false = low goal and true = high goal)
	 */
	private boolean getShooterMode() {
		m_shooterMode = shooterModeChooser.getSelected().booleanValue();
		return m_shooterMode;
	}

	/**
	 * Use this to set the shooter speed.
	 */
	public void setShooterSpeed() {
		if (getShooterMode()) {
			m_shooterSpeed = Constants.HIGH_SHOOTER_SPEED;
		} else {
			m_shooterSpeed = Constants.LOW_SHOOTER_SPEED;
		}
	}
}
