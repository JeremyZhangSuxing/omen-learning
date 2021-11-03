--
-- Created by IntelliJ IDEA.
-- User: suxingzhang
-- Date: 2021/10/27
-- Time: 5:02 下午
-- To change this template use File | Settings | File Templates.
--

local key = KEYS[1];
local threadId = ARGV[1];
local releaseTime = ARGV[2];

-- lockname不存在
if(redis.call('exists', key) == 0) then
    redis.call('hset', key, threadId, '1');
    redis.call('expire', key, releaseTime);
    return 1;
end;

-- 当前线程已id存在
if(redis.call('hexists', key, threadId) == 1) then
    redis.call('hincrby', key, threadId, '1');
    redis.call('expire', key, releaseTime);
    return 1;
end;
return 0;