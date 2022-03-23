package com.fireteam322.frc.robot.commands;

import com.fireteam322.frc.robot.subsystems.Talon;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.HashMap;

public class MotorBuilder extends CommandBase {

  private double m_speed;
  private Talon subsystem;
  private JoystickButton button;
  public static final String ACTION_HELD = "whenHeld", ACTION_RELEASED = "whenReleased";

  private HashMap<String, Double> speedCache;

  /** Creates a new RunShooter. */
  public MotorBuilder() {
    // m_shooter = shooter;
    // m_speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    // addRequirements(m_shooter);
    this.speedCache = new HashMap<String, Double>();
  }

  public MotorBuilder setDeviceID(int deviceId) {
    this.subsystem = new Talon(deviceId);
    return this;
  }

  public MotorBuilder setJoyStick(JoystickButton bindButton) {
    this.button = bindButton;
    /*bindButton.whenHeld(this,true);
    bindButton.whenReleased(this.setSpeed(0));*/
    return this;
  }

  public MotorBuilder setSpeed(Double speed) {
    m_speed = speed;
    return this;
  }

  public MotorBuilder setButtonActionAndSpeed(String action, Double speed) {
    this.speedCache.put(action, speed);
    switch (action) {
      case ACTION_HELD:
        this.button.whenHeld(this.setSpeed(this.speedCache.get(action)), true);
        break;
      case ACTION_RELEASED:
        this.button.whenReleased(this.setSpeed(this.speedCache.get(action)), true);
        break;
      default:
    }
    return this;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.subsystem.run(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (!interrupted) subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
