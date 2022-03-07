/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.fireteam322.frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.fireteam322.frc.robot.subsystems.AddressableLEDs;

public class AutomaticAddressableLED extends CommandBase {
	private final AddressableLEDs m_addressableLEDs;

	/**
	 * Creates a new AutomaticLED.
	 */
	public AutomaticAddressableLED(AddressableLEDs addressableLEDs) {
		m_addressableLEDs = addressableLEDs;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(m_addressableLEDs);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		m_addressableLEDs.StartLED();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		m_addressableLEDs.automaticLEDSetter();
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_addressableLEDs.StopLED();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public boolean runsWhenDisabled() {
		return true;
	}
}
