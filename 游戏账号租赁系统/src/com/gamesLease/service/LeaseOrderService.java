package com.gamesLease.service;

import com.gamesLease.bean.AccountInfo;
import com.gamesLease.bean.Game;
import com.gamesLease.bean.LeaseOrder;
import com.gamesLease.bean.User;
import com.gamesLease.dao.AccountInfoDAO;
import com.gamesLease.dao.GameDAO;
import com.gamesLease.dao.LeaseOrderDAO;
import com.gamesLease.dao.UserDAO;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wyx11 on 2017-5-23.
 */
public class LeaseOrderService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    private Map<Integer, Object> status;

    public LeaseOrderService() {
        status = new HashMap<>();
        status.put(-2, "审核未通过");
        status.put(-1, "已下架");
        status.put(0, "审核中");
        status.put(1, "可租用");
        status.put(2, "已被租");
    }

    public List<Map<String, Object>> getLeaseInfoMapList() {
        List<Map<String, Object>> leaseInfoMapList = null;

        AccountInfoDAO accountInfoDAO = new AccountInfoDAO();
        GameDAO gameDAO = new GameDAO();
        LeaseOrderDAO leaseOrderDAO = new LeaseOrderDAO();
        UserDAO userDAO = new UserDAO();

        List<LeaseOrder> leaseOrderList = leaseOrderDAO.queryLeaseOrder();
        leaseInfoMapList = new ArrayList<>();
        Iterator iterator = leaseOrderList.iterator();
        LeaseOrder leaseOrder = null;
        while (iterator.hasNext()) {
            leaseOrder = (LeaseOrder) iterator.next();

            Map<String, Object> leaseMap = new HashMap<>();
            User user = userDAO.getUserById(leaseOrder.getUid());
            AccountInfo accountInfo = accountInfoDAO.getAccountInfoById(leaseOrder.getAccountId());
            Game game = gameDAO.getGameById(accountInfo.getGameId());

            String start = dateFormat.format(leaseOrder.getStart());
            String end = dateFormat.format(leaseOrder.getEnd());


            leaseMap.put("user", user.getName());
            leaseMap.put("leaseId", leaseOrder.getId());
            leaseMap.put("game", game.getName());
            leaseMap.put("account", accountInfo.getAccount());
            leaseMap.put("password", accountInfo.getPassword());
            leaseMap.put("start", start);
            leaseMap.put("end", end);
            leaseMap.put("price", leaseOrder.getPrice());
            leaseMap.put("status", status.get(leaseOrder.getStatus()));
            leaseMap.put("description", leaseOrder.getDescription());

            leaseInfoMapList.add(leaseMap);
        }
        return leaseInfoMapList;
    }

    public List<Map<String, Object>> getLeaseInfoMapList(String gid, String description, Integer sta) {
        List<Map<String, Object>> leaseInfoMapList = null;

        AccountInfoDAO accountInfoDAO = new AccountInfoDAO();
        GameDAO gameDAO = new GameDAO();
        LeaseOrderDAO leaseOrderDAO = new LeaseOrderDAO();
        UserDAO userDAO = new UserDAO();

        List<LeaseOrder> leaseOrderList = null;
        leaseOrderList = leaseOrderDAO.queryLeaseOrderByDescription(description, sta);//根据描述查找
        if (!"all".equals(gid) && 0 != leaseOrderList.size()) {
            //根据游戏id过滤
            Iterator<LeaseOrder> iterator = leaseOrderList.iterator();//遍历链表,挑出符合游戏id和出租单
            while (iterator.hasNext()) {
                LeaseOrder leaseOrder = iterator.next();//获得leaseOrder
                AccountInfo accountInfo = accountInfoDAO.getAccountInfoById(leaseOrder.getAccountId());//根据leaseOrder的accountId找到Account
                if (!Integer.valueOf(gid).equals(accountInfo.getGameId())) {//如果gid!=此account的gameId,remove此leaseOrder
                    iterator.remove();
                }
            }
        }
        leaseInfoMapList = new ArrayList<>();
        Iterator iterator = leaseOrderList.iterator();
        LeaseOrder leaseOrder = null;
        while (iterator.hasNext()) {
            leaseOrder = (LeaseOrder) iterator.next();

            Map<String, Object> leaseMap = new HashMap<>();
            User user = userDAO.getUserById(leaseOrder.getUid());
            AccountInfo accountInfo = accountInfoDAO.getAccountInfoById(leaseOrder.getAccountId());
            Game game = gameDAO.getGameById(accountInfo.getGameId());

            String start = dateFormat.format(leaseOrder.getStart());
            String end = dateFormat.format(leaseOrder.getEnd());


            leaseMap.put("user", user.getName());
            leaseMap.put("leaseId", leaseOrder.getId());
            leaseMap.put("game", game.getName());
            leaseMap.put("account", accountInfo.getAccount());
            leaseMap.put("password", accountInfo.getPassword());
            leaseMap.put("start", start);
            leaseMap.put("end", end);
            leaseMap.put("price", leaseOrder.getPrice());
            leaseMap.put("status", status.get(leaseOrder.getStatus()));
            leaseMap.put("description", leaseOrder.getDescription());

            leaseInfoMapList.add(leaseMap);
        }
        return leaseInfoMapList;
    }

    public List<Map<String, Object>> getLeaseInfoMapList(Integer sta) {
        List<Map<String, Object>> leaseInfoMapList = null;

        AccountInfoDAO accountInfoDAO = new AccountInfoDAO();
        GameDAO gameDAO = new GameDAO();
        LeaseOrderDAO leaseOrderDAO = new LeaseOrderDAO();
        UserDAO userDAO = new UserDAO();

        List<LeaseOrder> leaseOrderList = leaseOrderDAO.queryLeaseOrderByStatus(sta);
        leaseInfoMapList = new ArrayList<>();
        Iterator iterator = leaseOrderList.iterator();
        LeaseOrder leaseOrder = null;
        while (iterator.hasNext()) {
            leaseOrder = (LeaseOrder) iterator.next();

            Map<String, Object> leaseMap = new HashMap<>();
            User user = userDAO.getUserById(leaseOrder.getUid());
            AccountInfo accountInfo = accountInfoDAO.getAccountInfoById(leaseOrder.getAccountId());
            Game game = gameDAO.getGameById(accountInfo.getGameId());

            String start = dateFormat.format(leaseOrder.getStart());
            String end = dateFormat.format(leaseOrder.getEnd());


            leaseMap.put("user", user.getName());
            leaseMap.put("leaseId", leaseOrder.getId());
            leaseMap.put("game", game.getName());
            leaseMap.put("account", accountInfo.getAccount());
            leaseMap.put("password", accountInfo.getPassword());
            leaseMap.put("start", start);
            leaseMap.put("end", end);
            leaseMap.put("price", leaseOrder.getPrice());
            leaseMap.put("status", status.get(leaseOrder.getStatus()));
            leaseMap.put("description", leaseOrder.getDescription());

            leaseInfoMapList.add(leaseMap);
        }
        return leaseInfoMapList;
    }

    public Map<String, Object> getLeaseInfoMap(Integer leaseId) {
        AccountInfoDAO accountInfoDAO = new AccountInfoDAO();
        GameDAO gameDAO = new GameDAO();
        LeaseOrderDAO leaseOrderDAO = new LeaseOrderDAO();
        UserDAO userDAO = new UserDAO();

        LeaseOrder leaseOrder = leaseOrderDAO.getLeaseOrderById(leaseId);

        Map<String, Object> leaseInfoMap = new HashMap<>();
        User user = userDAO.getUserById(leaseOrder.getUid());
        AccountInfo accountInfo = accountInfoDAO.getAccountInfoById(leaseOrder.getAccountId());
        Game game = gameDAO.getGameById(accountInfo.getGameId());

        String start = dateFormat.format(leaseOrder.getStart());
        String end = dateFormat.format(leaseOrder.getEnd());


        leaseInfoMap.put("user", user.getName());
        leaseInfoMap.put("leaseId", leaseOrder.getId());
        leaseInfoMap.put("game", game.getName());
        leaseInfoMap.put("account", accountInfo.getAccount());
        leaseInfoMap.put("password", accountInfo.getPassword());
        leaseInfoMap.put("start", start);
        leaseInfoMap.put("end", end);
        leaseInfoMap.put("price", leaseOrder.getPrice());
        leaseInfoMap.put("status", status.get(leaseOrder.getStatus()));
        leaseInfoMap.put("description", leaseOrder.getDescription());

        return leaseInfoMap;
}
}
