package com.fireteam322.frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.fireteam322.frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BottomShooter extends SubsystemBase {
  // The Shooter is both our upper level ball intake and our ball output
  // mechanism.
  private final WPI_TalonSRX m_leftShooterMotor = new WPI_TalonSRX(Constants.LEFT_SHOOTER_MOTOR);

  /** Creates a new Shooter. */
  public BottomShooter() {
    super();
    // Set the inversion on the shooter motors.
    m_leftShooterMotor.setInverted(false);

    // Set the shooter motors to Coast so they don't stop balls moving through them.
    m_leftShooterMotor.setNeutralMode(NeutralMode.Coast);
  }

  public void stop() {
    m_leftShooterMotor.stopMotor();
  }

  public void run(double speed) {
    m_leftShooterMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
