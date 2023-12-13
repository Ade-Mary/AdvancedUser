package com.Maryj.AdvancedUser.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class AdvancedUserResource extends RepresentationModel<AdvancedUserResource> {
  private   AdvancedUser advancedUser;
}
