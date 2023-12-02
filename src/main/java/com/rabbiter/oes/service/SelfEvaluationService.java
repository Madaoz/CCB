package com.rabbiter.oes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.*;

import java.util.List;

public interface SelfEvaluationService {

    List<SelfScore> findSelf(String userId);

    int updateScore(SelfScore selfScore);
}
