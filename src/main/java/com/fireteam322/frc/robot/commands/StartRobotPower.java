// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.fireteam322.frc.robot.commands;

import com.fireteam322.frc.robot.subsystems.RobotPower;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StartRobotPower extends CommandBase {
  private final RobotPower m_robotPower;

  /** Creates a new ClearPowerDistributionFaults. */
  public StartRobotPower(RobotPower robotPower) {
    m_robotPower = robotPower;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_robotPower);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_robotPower.resetTotalEnergy();
    //m_robotPower.clearStickyFaults();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
