import {addRule, removeRule, rule, updateRule} from '@/services/ant-design-pro/api';
import {PlusOutlined} from '@ant-design/icons';
import type {ActionType, ProColumns, ProDescriptionsItemProps} from '@ant-design/pro-components';
import {
  FooterToolbar,
  PageContainer,
  ProDescriptions,
  ProTable,
} from '@ant-design/pro-components';
import {FormattedMessage, useIntl} from '@umijs/max';
import {Button, Drawer, Input, message} from 'antd';
import React, {useRef, useState} from 'react';
// @ts-ignore
import {
  addInterfaceInfoUsingPOST,
  deleteInterfaceInfoUsingPOST,
  listInterfaceInfoByPageUsingGET,
  offlineInterfaceInfoUsingPOST,
  onlineInterfaceInfoUsingPOST,
  updateInterfaceInfoUsingPOST
  // @ts-ignore
} from "@/services/openapi-backend/interfaceInfoController";
import {SortOrder} from "antd/lib/table/interface";
import CreateModal from "@/pages/Admin/InterfaceInfo/components/CreateModal";
import UpdateModal from "@/pages/Admin/InterfaceInfo/components/UpdateModal";




const TableList: React.FC = () => {
  /**
   * @en-US Pop-up window of new window
   * @zh-CN 新建窗口的弹窗
   *  */
  const [createModalOpen, handleModalOpen] = useState<boolean>(false);
  /**
   * @en-US The pop-up window of the distribution update window
   * @zh-CN 分布更新窗口的弹窗
   * */
  const [updateModalOpen, handleUpdateModalOpen] = useState<boolean>(false);

  const [showDetail, setShowDetail] = useState<boolean>(false);

  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.InterfaceInfo>();
  const [selectedRowsState, setSelectedRows] = useState<API.InterfaceInfo[]>([]);

  /**
   * @en-US Add node
   * @zh-CN 添加节点
   * @param fields
   */
  const handleAdd = async (fields: API.InterfaceInfo) => {
    const hide = message.loading('正在添加');
    try {
      await addInterfaceInfoUsingPOST({...fields});
      hide();
      message.success('创建成功');
      handleModalOpen(false);
      actionRef.current?.reload();
      return true;
    } catch (error: any) {
      hide();
      message.error('创建失败，' + error.message);
      return false;
    }
  };


  /**
   * @zh-CN 更新节点
   *
   * @param fields
   */
  const handleUpdate = async (fields: API.InterfaceInfo) => {
    if(!currentRow){
      return;
    }
    const hide = message.loading('修改中');
    try {
      await updateInterfaceInfoUsingPOST({
        id:currentRow.id,
        ...fields
      });
      hide();
      message.success('更新成功！');
      actionRef.current?.reload();
      return true;
    } catch (error: any) {
      hide();
      message.error('更新失败' + error.message);
      return false;
    }
  };

  /**
   *  Delete node
   * @zh-CN 删除节点
   *
   * @param record
   */
  const handleRemove = async (record: API.InterfaceInfo) => {
    const hide = message.loading('正在删除');
    if (!record) return true;
    try {
      await deleteInterfaceInfoUsingPOST({
        id:record.id
      });
      hide();
      message.success('删除成功！');
      actionRef.current?.reload();
      return true;
    } catch (error:any) {
      hide();
      message.error('删除失败，'+error.message);
      return false;
    }
  };

  /**
   *  上线接口
   *
   * @param record
   */
  const handleOnline = async (record: API.IdRequest) => {
    const hide = message.loading('正在上线');
    if (!record) return true;
    try {
      await onlineInterfaceInfoUsingPOST({
        id:record.id
      });
      hide();
      message.success('上线成功！');
      actionRef.current?.reload();
      return true;
    } catch (error:any) {
      hide();
      message.error('上线失败，'+error.message);
      return false;
    }
  };

  /**
   *  下线接口
   *
   * @param record
   */
  const handleOffline = async (record: API.IdRequest) => {
    const hide = message.loading('正在下线');
    if (!record) return true;
    try {
      await offlineInterfaceInfoUsingPOST({
        id:record.id
      });
      hide();
      message.success('下线成功！');
      actionRef.current?.reload();
      return true;
    } catch (error:any) {
      hide();
      message.error('下线失败，'+error.message);
      return false;
    }
  };

  /**
   * @en-US International configuration
   * @zh-CN 国际化配置
   * */
  const intl = useIntl();

  const columns: ProColumns<API.InterfaceInfo>[] = [
    {
      title: '序号',
      dataIndex: 'id',
      valueType: 'index',
    },
    {
      title: '接口名称',
      dataIndex: 'name',
      valueType: 'text',
      formItemProps: {
        rules: [{
          required: true,
        }]
      }
    },
    {
      title: '描述',
      dataIndex: 'description',
      valueType: 'textarea',
      formItemProps: {
        rules: [{
          required: true,
        }]
      }
    },
    {
      title: 'URL',
      dataIndex: 'url',
      valueType: 'text',
      formItemProps: {
        rules: [{
          required: true,
        }]
      }
    },
    {
      title: '请求方法',
      dataIndex: 'method',
      valueType: 'text',
      formItemProps: {
        rules: [{
          required: true,
        }]
      }
    },
    {
      title: '请求参数',
      dataIndex: 'requestParams',
      valueType: 'jsonCode',
      formItemProps: {
        rules: [{
          required: true,
        }]
      }
    },
    {
      title: '请求头',
      dataIndex: 'requestHeader',
      valueType: 'jsonCode',
      formItemProps: {
        rules: [{
          required: true,
        }]
      }
    },
    {
      title: '响应头',
      dataIndex: 'responseHeader',
      valueType: 'jsonCode',
      formItemProps: {
        rules: [{
          required: true,
        }]
      }
    },
    {
      title: '状态',
      dataIndex: 'status',
      hideInForm: true,
      valueEnum: {
        0: {
          text: '关闭',
          status: 'Default',
        },
        1: {
          text: '开启',
          status: 'Processing',
        }
      },
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
      hideInForm: true
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      valueType: 'dateTime',
      hideInForm: true
    },

    {
      title:"操作",
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => [
        <a
          key="config"
          onClick={() => {
            handleUpdateModalOpen(true);
            setCurrentRow(record);
          }}
        >
          修改
        </a>,
        record.status===0?<a
          key="delect"
          onClick={() => {
            handleOnline(record);
          }}
        >
          上线
        </a>:null,
        record.status===1?<Button
          type={"text"}
          danger
          onClick={() => {
            handleOffline(record);
          }}
        >
          下线
        </Button>:null,
        <Button
          type={"text"}
          danger
          onClick={() => {
            handleRemove(record);
          }}
        >
          删除
        </Button>
      ],
    },
  ];


  return (
    <PageContainer>
      <ProTable<API.RuleListItem, API.PageParams>
        headerTitle={intl.formatMessage({
          id: 'pages.searchTable.title',
          defaultMessage: 'Enquiry form',
        })}
        actionRef={actionRef}
        rowKey="key"
        search={{
          labelWidth: 120,
        }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              handleModalOpen(true);
            }}
          >
            <PlusOutlined/> <FormattedMessage id="pages.searchTable.new" defaultMessage="新建"/>
          </Button>,
        ]}
        // @ts-ignore
        request={async (params: { pageSize?: number; current?: number; keyword?: string; },
                        sort: Record<string, SortOrder>,
                        filter: Record<string, (string | number)[] | null>) => {
          const res = await listInterfaceInfoByPageUsingGET({
            ...params
          })
          if (res?.data) {
            return {
              data: res?.data.records || [],
              success: true,
              total: res.data.total,
            }
          } else {
            return {
              data: [],
              success: false,
              total: 0,
            }
          }
        }
        }
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => {
            setSelectedRows(selectedRows);
          },
        }}
      />
      {selectedRowsState?.length > 0 && (

        <FooterToolbar

          extra={
            <div>
              <FormattedMessage id="pages.searchTable.chosen" defaultMessage="已选择"/>{' '}
              <a style={{fontWeight: 600}}>{selectedRowsState.length}</a>{' '}
              <FormattedMessage id="pages.searchTable.item" defaultMessage="项"/>
              &nbsp;&nbsp;
              <span>
                <FormattedMessage
                  id="pages.searchTable.totalServiceCalls"
                  defaultMessage="服务调用次数总计"
                />{' '}
                {/*{selectedRowsState.reduce((pre, item) => pre + item.callNo!, 0)}{' '}*/}
                <FormattedMessage id="pages.searchTable.tenThousand" defaultMessage="万"/>
              </span>
            </div>
          }
        >
          {/*<Button*/}
          {/*  onClick={async () => {*/}
          {/*    await handleRemove(selectedRowsState);*/}
          {/*    setSelectedRows([]);*/}
          {/*    actionRef.current?.reloadAndRest?.();*/}
          {/*  }}*/}
          {/*>*/}
          {/*  <FormattedMessage*/}
          {/*    id="pages.searchTable.batchDeletion"*/}
          {/*    defaultMessage="Batch deletion"*/}
          {/*  />*/}
          {/*</Button>*/}
          <Button type="primary">
            <FormattedMessage
              id="pages.searchTable.batchApproval"
              defaultMessage="批量审批"
            />
          </Button>
        </FooterToolbar>
      )}

      <UpdateModal
        columns={columns}
        onSubmit={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalOpen(false);
            setCurrentRow(undefined);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalOpen(false);
          if (!showDetail) {
            setCurrentRow(undefined);
          }
        }}
        visible={updateModalOpen}
        values={currentRow || {}}
      />

      <Drawer
        width={600}
        open={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={false}
      >
        {currentRow?.name && (
          <ProDescriptions<API.RuleListItem>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<API.RuleListItem>[]}
          />
        )}
      </Drawer>
      <CreateModal
        columns={columns}
        onCancel={() => handleModalOpen(false)}
        // @ts-ignore
        onSubmit={(values: API.InterfaceInfo) => handleAdd(values)}
        visible={createModalOpen}/>
    </PageContainer>
  );
};

export default TableList;
