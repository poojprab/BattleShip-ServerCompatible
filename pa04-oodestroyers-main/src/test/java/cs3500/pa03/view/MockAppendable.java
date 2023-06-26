package cs3500.pa03.view;

import java.io.IOException;

/**
 * Enables displaying messages and supports the Appendable interface for tests
 */
public class MockAppendable implements Appendable {

  /**
   * throws IOException error with message
   *
   * @throws IOException if there is an error
   */
  private void throwInOut() throws IOException {
    throw new IOException("Mock throwing an error");
  }

  /**
   * throws an IOException error
   *
   * @param csq The character sequence to append.  If {@code csq} is
   *            {@code null}, then the four characters {@code "null"} are
   *            appended to this Appendable.
   * @return returns null
   * @throws IOException if there is an error
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throwInOut();
    return null;
  }

  /**
   * throws an IOException error
   *
   * @param csq   The character sequence from which a subsequence will be
   *              appended.  If {@code csq} is {@code null}, then characters
   *              will be appended as if {@code csq} contained the four
   *              characters {@code "null"}.
   * @param start The index of the first character in the subsequence
   * @param end   The index of the character following the last character in the
   *              subsequence
   * @return returns null
   * @throws IOException if there is an error
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throwInOut();
    return null;
  }

  /**
   * throws an IOException error
   *
   * @param c The character to append
   * @return returns null
   * @throws IOException if there is an error
   */
  @Override
  public Appendable append(char c) throws IOException {
    throwInOut();
    return null;
  }
}