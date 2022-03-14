/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.fireteam322.frc.robot.subsystems;

import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.fireteam322.frc.robot.utilities.Limelight;
import com.fireteam322.frc.robot.utilities.Limelight.CameraMode;
import com.fireteam322.frc.robot.utilities.Limelight.LightMode;

public class LimelightCamera extends SubsystemBase {
	/**
	 * The Limelight subsystem incorporates the Limelight 2+ camera.
	 */
	private final Limelight m_limelight;
	private final HttpCamera m_limelightFeed;

	/**
	 * Creates a new Limelight.
	 */
	public LimelightCamera() {
		super();
		m_limelight = new Limelight();

		// Set the camera to Driver Mode
		m_limelight.setCameraMode(CameraMode.kdriver);

		// Turn off the lights
		m_limelight.setLedMode(LightMode.kforceOff);

		// Activate an HttpCamera for the Limelight
		m_limelightFeed = new HttpCamera("Limelight Camera", "http://10.3.22.11:5800/stream.mjpg", HttpCameraKind.kMJPGStreamer);
		//CameraServer.getInstance().startAutomaticCapture(m_limelightFeed);
	}

	/**
	 * This method exists to pass the Limelight object to other classes.
	 * @return Returns a Limelight object.
	 */
	public Limelight getLimelight() {
		return m_limelight;
	}

	public double getTX() {
		return m_limelight.getTX();
	}

	public double getTY() {
		return m_limelight.getTY();
	}

	public double getTA() {
		return m_limelight.getTA();
	}

	/**
	 * This method returns the Limelight HttpCamera feed.
	 * @return Returns an HttpCamera feed.
	 */
	public HttpCamera getLimelightFeed() {
		return m_limelightFeed;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
