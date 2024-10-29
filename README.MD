### Module Graph

```mermaid
%%{
  init: {
    'theme': 'base',
    'themeVariables': {"primaryTextColor":"#fff","primaryColor":"#5a4f7c","primaryBorderColor":"#5a4f7c","lineColor":"#f5a623","tertiaryColor":"#40375c","fontSize":"12px"}
  }
}%%

graph LR
  subgraph :core
    :core:navigation["navigation"]
    :core:dummy["dummy"]
    :core:common["common"]
    :core:common-ui["common-ui"]
    :core:designsystem["designsystem"]
    :core:resources["resources"]
    :core:local["local"]
  end
  subgraph :feature
    :feature:list["list"]
  end
  :core:navigation --> :domain
  :core:dummy --> :data
  :feature:list --> :core:common
  :feature:list --> :core:common-ui
  :feature:list --> :core:navigation
  :feature:list --> :domain
  :feature:list --> :core:designsystem
  :feature:list --> :core:resources
  :core:resources --> :core:common
  :core:common-ui --> :core:designsystem
  :core:common-ui --> :domain
  :core:common-ui --> :core:common
  :core:common-ui --> :core:resources
  :app --> :data
  :app --> :domain
  :app --> :core:designsystem
  :app --> :core:common
  :app --> :core:common-ui
  :app --> :core:resources
  :app --> :core:navigation
  :app --> :core:local
  :app --> :core:dummy
  :app --> :feature:list
  :data --> :domain
  :data --> :core:common
  :data --> :core:resources
  :data --> :core:local
  :domain --> :core:common
  :core:designsystem --> :domain
```