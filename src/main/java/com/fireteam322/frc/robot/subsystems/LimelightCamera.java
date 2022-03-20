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

	/**
	 * This method exists to pass whether the limelight has any valid targets.
	 * @return Returns true if there is a valid target.
	 */
	public boolean getTV() {
		return m_limelight.isTarget();
	}

	/**
	 * This method exists to pass the Horizontal Offset From Crosshair To Target.
	 * @return Returns the Horizontal Offset From Crosshair To Target in degrees.
	 */
	public double getTX() {
		return m_limelight.getTX();
	}

	/**
	 * This method exists to pass the Vertical Offset From Crosshair To Target.
	 * @return Returns the Vertical Offset From Crosshair To Target in degrees.
	 */
	public double getTY() {
		return m_limelight.getTY();
	}

	/**
	 * This method exists to pass the Target Area.
	 * @return Returns the Target Area in percent.
	 */
	public double getTA() {
		return m_limelight.getTA();
	}

	/**
	 * This method exists to pass the Skew/Rotation.
	 * @return Returns the Skew/Rotation in degrees.
	 */
	public double getTS() {
		return m_limelight.getTS();
	}

	/**
	 * This method exists to pass the pipeline latency.
	 * @return Returns the pipeline latency in milliseconds.
	 */
	public double getTL() {
		return m_limelight.getTL();
	}

	/**
	 * This method returns the Limelight HttpCamera feed.
	 * @return Returns an HttpCamera feed.
	 */
	public HttpCamera getLimelightFeed() {
		return m_limelightFeed;
	}

	// This method is called once per scheduler run
	@Override
	public void periodic() {}
}
