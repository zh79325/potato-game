package com.potato.core.service.impl;

import com.potato.common.common.Player;
import com.potato.core.app.PlayerSession;
import com.potato.core.app.SessionField;
import com.potato.core.app.impl.DefaultSession;

import java.util.Map;

import static com.potato.core.app.SessionField.PLAYER;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 17:47
 * Description:
 */
public class PlayerSessionImpl extends DefaultSession implements PlayerSession {

    static String buildId(String uid, String from) {
        return String.format("user:%s:%s", uid, from);
    }

    final String uid;
    final String from;

    public PlayerSessionImpl(String uid, String from) {
        super(buildId(uid, from));
        this.uid = uid;
        this.from = from;
    }

    Player player;

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public PlayerSession createNewSession(Player player) {
        PlayerSessionBuilder playBuilder=new PlayerSessionBuilder(player);
        super.buildNewSession(playBuilder);
        return this;
    }

    @Override
    protected String buildId() {
        return buildId(uid, from);
    }

    public static class PlayerSessionBuilder extends SessionBuilder{
        Player player;
        public PlayerSessionBuilder(Player player) {
            this.player=player;
        }

        @Override
        public Map<SessionField, Object> build() {
            this.data(PLAYER,player);
            return super.build();

        }
    }


}
