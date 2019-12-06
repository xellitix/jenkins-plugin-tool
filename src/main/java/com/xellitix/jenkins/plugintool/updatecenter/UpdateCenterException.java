package com.xellitix.jenkins.plugintool.updatecenter;

/**
 * {@link UpdateCenter} exception.
 *
 * @author Grayson Kuhns
 */
public class UpdateCenterException extends RuntimeException {

  /**
   * Constructor.
   *
   * @param reason The reason for the failure.
   */
  public UpdateCenterException(final String reason) {
    super(reason);
  }

  /**
   * Constructor.
   *
   * @param cause The cause of the failure.
   */
  public UpdateCenterException(final Throwable cause) {
    super(cause);
  }
}
