package com.wednesday.assignment.relaxicab.controller.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse   {
  @JsonProperty("code")
  private String code = null;

  private String message = null;

  private String rootCause = null;


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.code, errorResponse.code) &&
        Objects.equals(this.message, errorResponse.message) &&
        Objects.equals(this.rootCause, errorResponse.rootCause);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, rootCause);
  }

  @Override
  public String toString() {

    return "class ErrorResponse {\n" +
            "    code: " + toIndentedString(code) + "\n" +
            "    message: " + toIndentedString(message) + "\n" +
            "    rootCause: " + toIndentedString(rootCause) + "\n" +
            "}";
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

