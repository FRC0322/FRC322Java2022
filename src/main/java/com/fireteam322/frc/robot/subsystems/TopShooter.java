package com.fireteam322.frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.fireteam322.frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TopShooter extends SubsystemBase {
  // The Shooter is both our upper level ball intake and our ball output
  // mechanism.
  private final WPI_TalonSRX m_rightShooterMotor = new WPI_TalonSRX(Constants.RIGHT_SHOOTER_MOTOR);

  /** Creates a new Shooter. */
  public TopShooter() {
    super();
    // Set the inversion on the shooter motors.

    m_rightShooterMotor.setInverted(false);

    // Set the shooter motors to Coast so they don't stop balls moving through them.
    m_rightShooterMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void stop() {
    m_rightShooterMotor.stopMotor();
  }

  public void run(double speed) {
    m_rightShooterMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
