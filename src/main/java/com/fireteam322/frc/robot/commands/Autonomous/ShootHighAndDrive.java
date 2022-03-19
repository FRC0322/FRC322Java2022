/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.fireteam322.frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.fireteam322.frc.robot.Constants;
import com.fireteam322.frc.robot.subsystems.Chassis;
import com.fireteam322.frc.robot.subsystems.Feeder;
import com.fireteam322.frc.robot.subsystems.Intake;
import com.fireteam322.frc.robot.subsystems.Shooter;

public class ShootHighAndDrive extends CommandBase {
	private final Chassis m_chassis;
	private final Intake m_intake;
	private final Feeder m_feeder;
	private final Shooter m_shooter;
	private double startTime;

	/**
	 * Creates a new SimpleAutonomous.
	 */
	public ShootHighAndDrive(Chassis chassis, Intake intake, Feeder feeder, Shooter shooter) {
		m_chassis = chassis;
		m_intake = intake;
		m_feeder = feeder;
		m_shooter = shooter;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(m_chassis, m_shooter);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		startTime = Timer.getFPGATimestamp();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if (Timer.getFPGATimestamp() < (startTime + 1.0)) {
			m_shooter.run(Constants.HIGH_SHOOTER_SPEED);
		} else if ((Timer.getFPGATimestamp() >= (startTime + 1.0)) && Timer.getFPGATimestamp() <= (startTime + 1.5)) {
			m_feeder.run(Constants.FEEDER_SPEED);
		} else {
			m_shooter.stop();
			m_feeder.stop();
			m_chassis.drive(Constants.DEFAULT_AUTONOMOUS_SPEED, Constants.DEFAULT_AUTONOMOUS_HEADING);
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_chassis.stop();
		m_intake.stop();
		m_feeder.stop();
		m_shooter.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (Timer.getFPGATimestamp() < (startTime + Constants.DEFAULT_AUTONOMOUS_TIME)) {
			return false;
		} else {
			return true;
		}
	}
}
