package com.spiashko.restpersistence.jacksonjpa.selfrefresolution;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(SelfReferenceResolutionConstants.SELF_REFERENCE_RESOLUTION_FILTER)
public class SelfReferenceResolutionFilterMixin {

}
