package com.fireteam322.frc.robot.commands;

import com.fireteam322.frc.robot.subsystems.BottomShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunBottomShooter extends CommandBase {
  private final BottomShooter m_shooter;
  private final double m_speed;

  /** Creates a new RunShooter. */
  public RunBottomShooter(BottomShooter shooter, double speed) {
    m_shooter = shooter;
    m_speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.run(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (!interrupted) m_shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
