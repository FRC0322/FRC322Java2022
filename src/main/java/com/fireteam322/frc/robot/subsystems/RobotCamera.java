/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.fireteam322.frc.robot.subsystems;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RobotCamera extends SubsystemBase {
	private static UsbCamera cameraServer;

	/**
	 * Creates a new FrontCamera.
	 */
	public RobotCamera(String name, int port) {
		// Setup Camera
		cameraServer = new UsbCamera(name, port);
	}

	public void setResolution(int width, int height) {
		cameraServer.setResolution(width, height);
	}

	/**
	 * This method returns the Limelight HttpCamera feed.
	 *
	 * @return Returns a UsbCamera feed.
	 */
	public UsbCamera getCameraFeed() {
		return cameraServer;
	}

	public void setFPS(int fps) {
		cameraServer.setFPS(fps);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
