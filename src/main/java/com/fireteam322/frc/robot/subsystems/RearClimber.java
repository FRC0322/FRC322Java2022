// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.fireteam322.frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.fireteam322.frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RearClimber extends SubsystemBase {
  private final WPI_TalonFX m_leftClimberMotor = new WPI_TalonFX(Constants.REAR_LEFT_CLIMBER_MOTOR);
  private final WPI_TalonFX m_rightClimberMotor =
      new WPI_TalonFX(Constants.REAR_RIGHT_CLIMBER_MOTOR);
  private final MotorControllerGroup m_climberMotors =
      new MotorControllerGroup(m_leftClimberMotor, m_rightClimberMotor);

  /** Creates a new RearClimber. */
  public RearClimber() {
    super();
    /*
     * // Set the inversion on the climber motors. (Until we know if this is
     * necessary, it'll remain commented out.)
     * m_leftClimberMotor.setInverted(false);
     * m_rightClimberMotor.setInverted(false);
     * m_climberMotors.setInverted(true);
     */
    // Set the climber motors to Brake so the robot doesn't fall when unpowered.
    m_leftClimberMotor.setNeutralMode(NeutralMode.Brake);
    m_rightClimberMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void stop() {
    m_climberMotors.stopMotor();
  }

  public void run(double speed) {
    m_climberMotors.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
