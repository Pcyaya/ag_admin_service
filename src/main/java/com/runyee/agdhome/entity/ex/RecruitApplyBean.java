package com.runyee.agdhome.entity.ex;
import com.runyee.agdhome.entity.db.ag_home.AhRecruitApply;

/**
 * Created by cheri on 2018/8/7.
 */
public class RecruitApplyBean extends AhRecruitApply {
    private int resume_flg;//是否有简历 1.有0.无

    public int getResume_flg() {
        return resume_flg;
    }

    public void setResume_flg(int resume_flg) {
        this.resume_flg = resume_flg;
    }
}
