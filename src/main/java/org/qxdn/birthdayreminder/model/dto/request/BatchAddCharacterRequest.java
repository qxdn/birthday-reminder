package org.qxdn.birthdayreminder.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BatchAddCharacterRequest {

    List<AddCharacterRequest> batchData;
}
