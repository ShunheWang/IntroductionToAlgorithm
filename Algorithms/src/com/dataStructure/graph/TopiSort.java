package com.dataStructure.graph;

//public class Solution {
//    public:
//    bool dfs_improve(int v,vector<vector<int>>&od,vector<int>&visited,vector<int>&loopvisited,stack<int>&st){
//        visited[v]=1;//标记访问过的节点，到一个点，标记一个点
//        for(int &i:od[v]){
//            if(!loopvisited[i]&&visited[i])return true;//如果loopvisited没有标记为访问，但visited却标记为访问了，表示这个节点在本次深度优先搜索时已经访问过，即存在环路。
//            if(!visited[i]){
//                bool loopcheck=dfs_improve(i,od,visited,loopvisited,st);
//                if(loopcheck)return true;
//            }
//        }
//        loopvisited[v]=1;//标记访问过的节点，但到整个深度优先搜索结束后才标记，所以loopvisited已作标记的是之前本次搜索之前就访问过的节点。
//        st.push(v);
//        return false;
//    }
//    vector<int> findOrder(int nc,vector<pair<int, int>>& pre) {
//        vector<int>visited(nc,0);//访问标记
//        vector<int>loopvisited(nc,0);//环路检测访问标记
//        vector<vector<int>>od(nc);//每个点的邻接点集
//        stack<int>st;//辅助栈
//        for(int i=0;i<(int)pre.size();i++){
//            od[pre[i].second].push_back(pre[i].first);
//        }
//        vector<int>res;//用于保存拓扑排序
//        bool hasloop=false;//是否存在环路
//        for(int i=0;i<nc;i++){
//            if(0==visited[i]){
//                if(dfs_improve(i,od,visited,loopvisited,st))hasloop=true;//DFS（改良）
//            }
//        }
//        if(hasloop)return res;//如果存在环路，返回空集
//        while(!st.empty()){//构造拓扑排序
//            res.push_back(st.top());
//            st.pop();
//        }
//        return res;
//    }
//};
