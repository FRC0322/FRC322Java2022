// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.fireteam322.frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.fireteam322.frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FrontClimber extends SubsystemBase {
  private final WPI_TalonFX m_climberMotor = new WPI_TalonFX(Constants.FRONT_CLIMBER_MOTOR);

  /** Creates a new FrontClimber. */
  public FrontClimber() {
    super();
    // Set the inversion on the climber motor. (Until we know if this is necessary,
    // it'll remain commented out.)
    // m_climberMotor.setInverted(true);

    // Set the climber motor to Brake so the robot doesn't fall when unpowered.
    m_climberMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void stop() {
    m_climberMotor.stopMotor();
  }

  public void run(double speed) {
    m_climberMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
