/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.fireteam322.frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.fireteam322.frc.robot.Constants;

public class RobotPower extends SubsystemBase {
	/**
	 * Creates a new RobotPower.
	 */
	private final PowerDistribution m_pdp = new PowerDistribution(Constants.PDP_CHANNEL, PowerDistribution.ModuleType.kCTRE);
	public RobotPower() {
		super();

		m_pdp.resetTotalEnergy();
	}

	public double getCurrent(int channel) {
		return m_pdp.getCurrent(channel);
	}

	public double getVoltage() {
		return m_pdp.getVoltage();
	}

	public double getTotalCurrent() {
		return m_pdp.getTotalCurrent();
	}

	public double getTemperature() {
		return m_pdp.getTemperature();
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
