package com.fireteam322.frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Talon extends SubsystemBase {
  // The Shooter is both our upper level ball intake and our ball output
  // mechanism.
  private WPI_TalonSRX talon;

  /** Creates a new Shooter. */
  public Talon(int deviceId) {
    super();
    talon = new WPI_TalonSRX(deviceId);
    // Set the inversion on the shooter motors.
    talon.setInverted(false);
    // Set the shooter motors to Coast so they don't stop balls moving through them.
    talon.setNeutralMode(NeutralMode.Brake);
  }

  public void stop() {
    talon.stopMotor();
  }

  public void run(double speed) {
    talon.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
