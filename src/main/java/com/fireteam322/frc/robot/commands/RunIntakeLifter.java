// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.fireteam322.frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.fireteam322.frc.robot.subsystems.IntakeLifter;

public class RunIntakeLifter extends CommandBase {
  private final IntakeLifter m_lifter;
  private final double m_speed;

  /** Creates a new RunIntakeLifter. */
  public RunIntakeLifter(IntakeLifter lifter, double speed) {
    m_lifter = lifter;
    m_speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_lifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_lifter.run(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_lifter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
