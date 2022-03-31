// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.fireteam322.frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants.
 * This class should not be used for any other purpose.
 * All constants should be declared globally (i.e. public static).
 * Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
	// AddressableLEDs.java
	public static final int ADDRESSABLE_LED_PORT = 0,
			ADDRESSABLE_LED_LENGTH = 300;

	// AutomaticAddressableLED.java
	public static final double AUTONOMOUS_BLINK_RATE = 0.25,
			TELOP_BLINK_RATE = 0.5,
			ESTOP_BLINK_RATE = 0.05,
			DISABLED_BLINK_RATE = 0.0,
			DEFAULT_BLINK_RATE = 2.5;

	// Chassis.java
	public static final int DRIVE_LEFTFRONT = 3,
			DRIVE_LEFTREAR = 4,
			DRIVE_RIGHTFRONT = 1,
			DRIVE_RIGHTREAR = 2,
			TICKS_PER_INCH = 512;

	// Intake.jave
	public static final int INTAKE_MOTOR = 5;

	// Feeder.java
	public static final int FEEDER_MOTOR = 6;

	// Shooter.java
	public static final int LEFT_SHOOTER_MOTOR = 7;
	public static final int RIGHT_SHOOTER_MOTOR = 8;

	// IntakeLifter.java
	public static final int INTAKE_LIFT_MOTOR = 9;

	// RobotContainer.java
	public static final boolean CLASSIC_MODE = true,
			ROBOT_POWER = false,
			DEBUG_MODE = false;
	public static final int DRIVE_STICK = 0,
			MANIPULATOR_STICK = 1,
			DEBUGGER_STICK = 2,
			LEFT_DRIVE_STICK = 3,
			RIGHT_DRIVE_STICK = 4,
			CAMERASERVER_FEEDS = 2,
			FRONT_CAMERA_CHANNEL = 0,
			JOYSTICK_BRAKE_BUTTON = 4,
			VISION_MODE_BUTTON = 5;
	public static final double FEEDER_SPEED = 1.0,
			FEEDER_REVERSE_SPEED = -(0.75),
			INTAKE_LIFT_SPEED = 0.25,
			INTAKE_LOWER_SPEED = -(0.25),
			INTAKE_SPEED = 1.0,
			INTAKE_REVERSE_SPEED = -(1.0),
			LOW_SHOOTER_SPEED = 0.45,
			HIGH_SHOOTER_SPEED = 0.90,
			SHOOTER_REVERSE_SPEED = -(0.5),
			CLIMBER_SPEED = 0.6,
			CLIMBER_REVERSE_SPEED = -(0.4);

	// RearClimber.java
	public static final int REAR_LEFT_CLIMBER_MOTOR = 10,
			REAR_RIGHT_CLIMBER_MOTOR = 11;

	// RunFrontCamera.java
	public static final int VIDEO_WIDTH = 640,
			VIDEO_HEIGHT = 480,
			VIDEO_FPS = 30;

	// Robot Power
	public static final int PDP_CHANNEL = 0;

	// Autonomous
	public static final double DEFAULT_AUTONOMOUS_DISTANCE = 24.0,
			DEFAULT_AUTONOMOUS_SPEED = -(0.5),
			DEFAULT_AUTONOMOUS_HEADING = 0.0,
			AUTONOMOUS_DISTANCE_ERROR_FACTOR = 6.0,
			DEFAULT_AUTONOMOUS_TIME = 10.0,
			SHOOTER_AUTONOMOUS_DRIVETIME = 3.0;
}
