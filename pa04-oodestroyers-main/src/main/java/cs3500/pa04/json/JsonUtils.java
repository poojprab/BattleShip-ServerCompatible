package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple utils class used to hold static methods that help with serializing and deserializing JSON.
 */
public class JsonUtils {
  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   *         ^ we had this method throw the exception because we tried creating a mock record that
   *         would fail being converted (for testing a try catch block) and we couldn't figure out
   *         any way to do this
   */
  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(record, JsonNode.class);
  }
}
