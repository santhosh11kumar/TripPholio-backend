package com.tripfolio.locationService.domain.util;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class ResponseBody<T> {

    private  T data;

}
