// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.fireteam322.frc.robot.commands;

import com.fireteam322.frc.robot.subsystems.RearClimber;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunRearClimber extends CommandBase {
  private final RearClimber m_climber;
  private final double m_speed;

  /** Creates a new RunRearClimber. */
  public RunRearClimber(RearClimber climber, double speed) {
    m_climber = climber;
    m_speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climber.run(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
