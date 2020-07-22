package com.today.roc.go.dto.response.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年04月13日 18:15*
 * log.info()
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyLabelVo implements Serializable {

    private String key;

    private String label;

}
